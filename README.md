# book_search_kakao_api

카카오톡 Api를 사용하여 책을 검색하고 즐겨찾기를 등록할수있는 앱을 구현하였습니다.


Version Catalog + Convention Plugin을 기반으로한 멀티 모듈을 사용하여 앱을 구현하였으며
Compose + MVI구조과 Clean Architectur를 적용하였습니다.

[프로젝트 구조]
- app
- build-logic
- core
  - data
  - database
  - domain
  - model
  - navigation
  - ui
- feature
  - detail
  - favorite
  - main
  - search

[사용 기술]

Compose + MVI, Hilt, Clean Architectur, Version Catalog + Convention Plugin, Retrofit2, Paging3, Room, Coil, Coroutune/Flow, 
