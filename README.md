# 츠누봇 모바일 애플리케이션 서버

<div id="top"></div>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/cnu-bot">
    <img src="https://user-images.githubusercontent.com/69495129/191981078-ce719995-d227-43b3-98f7-12e656336faf.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">🌎Cnubot Server🌎</h3>
  <p align="center">
    <br />
    <a href="https://github.com/cnu-bot"><strong>Explore the Organization</strong></a>
    <br />
    <br />
    <!-- <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a> -->
    <!-- · -->
    <a href="https://github.com/cnu-bot/cnubot-server-spring/issues">Issues</a>
    ·
    <a href="https://github.com/orgs/cnu-bot/projects/3">MileStone</a>
    ·
    <a href="https://github.com/cnu-bot/cnubot-server-spring/pulls?q=is%3Apr+is%3Aclosed">Pull Request</a>

</p>
</div>

## Description
- ![Generic badge](https://img.shields.io/badge/version-1.0-green.svg)
- 충남대 정보제공 앱인 츠누봇의 모바일 백엔드 오픈소스

### Framework
<img src="https://img.shields.io/badge/Spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<br>

### Project
<img src="https://img.shields.io/badge/gradle-4479A1?style=for-the-badge&logo=gradle&logoColor=white">

### Test
<img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white">

### Database
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Amazon Ec2-FF9900?style=for-the-badge&logo=Amazon Ec2&logoColor=white">

<br>

### Infra
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">


## Commit Convention

feat: 새로운 기능에 대한 커밋  
fix: 버그 수정에 대한 커밋  
hotfix: main or develop 브랜치에서 긴급 수정해야할 경우
chore: 그 외 자잘한 수정에 대한 커밋  
docs: README.md 수정에 대한 커밋  
refactor: 코드 리팩토링 (최적화 개선)

## Architecture

<br>

```yaml

├─main
│  ├─java
│  │  └─com
│  │      └─cnubot
│  │          └─cnubotserver
│  │              ├─appconfig ## Configuration
│  │              ├─board ## Board Domain
│  │              │  ├─component
│  │              │  ├─config
│  │              │  ├─controller
│  │              │  ├─entity
│  │              │  ├─repository
│  │              │  └─service
│  │              ├─exception ## Project ExceptionHandler
│  │              ├─foodcourt ## Food Domain
│  │              │  ├─component
│  │              │  ├─controller
│  │              │  │  └─dto
│  │              │  ├─entity
│  │              │  ├─enums
│  │              │  ├─repository
│  │              │  └─service
│  │              │      └─crawling
│  │              └─swagger ## API Docs
│  └─resources
└─test

```

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. check our issue list
3. Create your Feature Branch (`git checkout -b feat-AmazingFeature`) we have our convention
4. Commit your Changes (`git commit -m 'feat: I made it'`)
5. Push to the Branch (`git push origin feat-AmazingFeature`)
6. Open a Pull Request (we have our PR templates)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->

## Contact

Chanhyuk Park - [GitHub](https://github.com/ChanhyukPark-Tech) - chanhyuk-tech@kakao.com


<p align="right">(<a href="#top">back to top</a>)</p>

## 🌟 Contributors

[![contributors](https://contrib.rocks/image?repo=cnu-bot/cnubot-server-spring)](https://github.com/cnu-bot/cnubot-client-app/graphs/contributors)

# cnubot-server-spring

