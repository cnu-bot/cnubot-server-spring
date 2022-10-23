package com.cnubot.cnubotserver.domain.entity;

/**
 * 2단계 DEPTH
 * 열거 순으로
 * 새소식, 학사정보, CNUNEWS, 교육정보, 사업단 창업, 교육, 채용 초빙, 백마게시판
 *
 * STUDENT RECRUITING, EVENT, JOB&CAREER, FORM&DATA
 */
public enum DepthSecond {
    NEWS("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0701&site_dvs_cd=kr&menu_dvs_cd=0701") //번호, 제목, 작성자, 작성일, 조회수, 파일여부
    , ACADEMIC_INFO("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0702&site_dvs_cd=kr&menu_dvs_cd=0702") //번호, 제목, 작성자, 작성일, 조회수, 파일여부
    , EDUCATION_INFO("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0704&site_dvs_cd=kr&menu_dvs_cd=0704")
    , BUSINESS_GROUP_STARTUP("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0709&site_dvs_cd=kr&menu_dvs_cd=0709")
    , UNIV_CULTURE_FIELD("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_070801&site_dvs_cd=kr&menu_dvs_cd=070801")
    , CNU_MARKET("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_070802&site_dvs_cd=kr&menu_dvs_cd=070802")
    , STUDY_COMPETITION("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_070808&site_dvs_cd=kr&menu_dvs_cd=070808")//
    , RECRUIT_INVITATION("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0705&site_dvs_cd=kr&menu_dvs_cd=0705")//번호, 제목, 작성자, 모집기한, 작성일, 조회수
    , JOB_SEARCH("https://plus.cnu.ac.kr/_prog/recruit/?menu_dvs_cd=07080401&site_dvs_cd=kr") //번호, 제목, 작성자, 모집기한, 작성일, 조회수
    , JOB_OFFER("https://plus.cnu.ac.kr/_prog/recruit/?site_dvs_cd=kr&menu_dvs_cd=07080402&gubun=2")//번호, 제목, 작성자, 모집기한, 작성일, 조회수
    , CNU_NEWS("https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0703&site_dvs_cd=kr&menu_dvs_cd=0703")
    , STUDENT_RECRUIT("https://cnuint.cnu.ac.kr/cnuint/notice/recruit.do")
    , EVENT("https://cnuint.cnu.ac.kr/cnuint/notice/event.do")
    , JOB_CAREER("https://cnuint.cnu.ac.kr/cnuint/notice/job.do")
    , FORM_DATA("https://cnuint.cnu.ac.kr/cnuint/notice/form.do");

    private String port;
    private String url;
    public String getPort(){
        return port;
    }
    DepthSecond(String port) {
        this.port=port;
        this.url="https://plus.cnu.ac.kr/_prog/_board/?code=sub07_0704&site_dvs_cd=kr&menu_dvs_cd=";
    }
}
