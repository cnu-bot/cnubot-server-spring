package com.cnubot.cnubotserver.foodcourt.service.crawling;

import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import com.cnubot.cnubotserver.foodcourt.enums.Time;
import com.cnubot.cnubotserver.foodcourt.enums.Week;
import com.cnubot.cnubotserver.foodcourt.repository.MenuRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FoodCrawling {

    MenuRepository repository;

    public void process() {
        FoodCourt[] values = FoodCourt.values();
        for (FoodCourt value : values) {
            if (value == FoodCourt.DORMITORY) {
                getDormitory(value);
            }
            getStudentHall(value);
        }
    }


    private Document getDoc(String url) {
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private void getStudentHall(FoodCourt foodCourt) {
        List<String> foods;
        Document document = getDoc(foodCourt.getPort());
        Elements elements = document.select("tr");

        for (int i = 2; i < 5; i++) { // 아침 학생 점심 학생, 교직
            Time time;
            if (i == 2) {
                time = Time.BREAKFAST;
            } else {
                time = Time.LUNCH;
            }
            Element tr = elements.get(i);
            String typeFirst;
            int index;
            if (tr.select("td").size() == 8) {
                typeFirst = (tr.select("td").get(1).text()); //
                index = 2;
            } else {
                typeFirst = (tr.select("td").get(0).text()); //
                index = 1;
            }
            for (int j = index; j < index + 5; j++) { // 월화수목금토
                Element td = tr.select("td").get(j);
                Elements li = td.select("li");
                Elements types = li.select("h3");
                Elements foodByP = li.select("p");
                for (int k = 0; k < types.size(); k++) { // 하나의 식사에 양식 일품 두가지 있을 때를 대비
                    foods = new ArrayList(Arrays.asList(foodByP.get(k).text().split(" ")));
                    String typeSecond = " " + types.get(k).text();
                    Menu menu = Menu.builder()
                            .day(Week.values()[j - index])
                            .type(typeFirst + typeSecond)
                            .foods(foods)
                            .time(time)
                            .foodCourt(foodCourt)
                            .build();
                    repository.save(menu);
                }
            }
        }
    }

    private void getDormitory(FoodCourt foodCourt) {
        Document document = getDoc(foodCourt.getPort());
        Elements tds = document.select(".diet_table").select("tbody").select("td");

        for (int i = 0; i < tds.size(); i += 4) {
            String breakfast = removeEng(tds.get(i + 1).select(".left").text()); // 아침
            String lunch = removeEng(tds.get(i + 2).select(".left").text()); // 점심
            String dinner = removeEng(tds.get(i + 3).select(".left").text()); // 저녁
            String day = getDay(tds.get(i).text());
            dormitorySave(breakfast, day, Time.BREAKFAST);
            dormitorySave(lunch, day, Time.LUNCH);
            dormitorySave(dinner, day, Time.DINNER);
        }
    }

    private void dormitorySave(String target, String day, Time time) {
        System.out.println(day);
        Optional<Week> findDay = Arrays.stream(Week.values()).filter(s -> s.getDay().equals(day)).findFirst();

        List<String> list = new ArrayList<>(Arrays.asList(target.split(" ")));
        Optional<String> element = list.stream().filter(s -> s.contains("메인C")).findFirst();
        String typeOfA = list.get(0);
        List<String> listOfMainA = list;
        if (element.isPresent()) {
            int indexOfMainC = list.indexOf(element.get());
            String typeOfC = list.get(indexOfMainC);
            listOfMainA = list.subList(1, indexOfMainC);
            List<String> listOfMainC = list.subList(indexOfMainC + 1, list.size());
            Menu menuC = Menu.builder()
                    .time(time)
                    .foods(listOfMainC)
                    .foodCourt(FoodCourt.DORMITORY)
                    .type(typeOfC)
                    .day(findDay.get())
                    .build();
            repository.save(menuC);
        }
        Menu menuA = Menu.builder()
                .time(time)
                .foods(listOfMainA)
                .foodCourt(FoodCourt.DORMITORY)
                .type(typeOfA)
                .day(findDay.get())
                .build();
        repository.save(menuA);

    }

    private String removeEng(String data) {
        return data.split("Main")[0];
    }

    private String getDay(String data) {
        int end = data.indexOf(")");
        String day = data.substring(end - 1, end);
        return day;
    }

}
