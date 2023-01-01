# Spring-Project
너와 농부싶어 ver.2


## 👩‍💻 프로젝트 소개
'코멘토' 사이트를 벤치마킹하여 만든 2030 청년 귀농 지원 사이트입니다.

귀농귀촌에 관심이 있거나 원하는 사람들에게 필요한 전문 지식 및 정보들을 안내하고 <br>
서로 간의 커뮤니티와 이용할 수 있는 다양한 프로그램, 아르바이트 정보를 제공하여 <br> 
귀농귀촌에 대한 접근성 향상 도모

JSP 프로젝트와 동일한 주제로, 더 트렌디한 사이트로 리뉴얼되었고, 새로운 멘토링 기능이 추가되었습니다.

## ⏱️ 개발 기간
- 22.11.01 ~ 22.12.20

## 🧑🏻‍🤝‍🧑🏼 맡은 역할
- 부팀장 : ERD 구성 및 설계
- 퍼블리싱 : 프로그램
- 백엔드 : 회원가입&로그인(Oauth), 마이페이지, 메인페이지

## 🖱️ 개발 환경
![슬라이드0006](https://user-images.githubusercontent.com/109491137/210177896-587d1850-523f-4011-8e96-169e6e31baef.jpg)

## 📌 느낀점
- 만족한 부분

  - JSP 프로젝트 때 ajax를 이용한 정렬기능을 구현하지 못해서 아쉬웠는데, 이번에는 아예 restful 방식을 사용하여 @RestController를 만들었고, ajax를 활용하여 html 페이지를 작성하였다. 이전에 못했던 부분을 이번 프로젝트에서 할 수 있게 되어 실력이 향상됨을 느꼈고, 공부에 대한 열의가 더 생기기도 하였다.
  - Oauth를 사용하여 회원가입/로그인을 기능을 구현할 수 있었다. 카카오, 네이버, 구글 로그인을 구현해야 해서 카카오부터 시작을 했는데, 처음에는 너무 감을 못잡았다가 카카오를 완성하고 나니 바로 감잡고 네이버는 수월하게 완료하였다. 구글은 좀 더 많은 검색을 하였고, 팀원과 논의하며 하나씩 풀어나갔다.
  - 우리 사이트는 멤버 타입에 따라 제공되는 서비스가 달라서 타입 구분이 필요했다. 그래서 초반에 회원 공통적인 부분은 추상 클래스로 만들고 그것을 상속받아 타입을 나누어서 각자 테이블을 만드는 것으로 설계를 진행했었다. 하지만, 회원가입할 때 회원이 선택해서 타입이 정해지는데, 이 경우에 회원 저장할 때 어떤 식으로 해야 하는 지 막혔었고, 상속받은 자식 테이블에 있는 컬럼이 타입 컬럼밖에 없다면, 굳이 추상클래스를 만들어서 상속받을 필요 없이 하나의 멤버 테이블에 타입 컬럼에 각자 타입을 넣으면 된다고 강사님에게 피드백을 받았다. 피드백을 받았을 때가 백작업이 들어간 지 일주일이 넘은 상태라 엔티티를 새로 만들고, create 하기에 부담이 있었는데, 팀원들도 이렇게 하는 게 더 나을 것 같다고 동의하였고, 큰 불만 없이 협조해주어서 빠른 시간에 엔티티 변경 작업을 끝마치고, 충돌 오류를 해결하고 새로운 설계로 다시 백작업을 빠르게 진행할 수 있었다.
  
- 아쉬웠던 부분

  - spring, jpa를 배웠는데 바로 프로젝트에 적용하기가 어려웠다. 이해가 됐다고 생각했는데 어떤 식으로 코드를 작성해야 할 지 감이 안 잡혔고, 어디서부터 시작해야 할 지 막막했다. 그래서 강사님에게 양해를 구하고 2-3일 정도 복습에 집중했다. 아무래도 배경지식이 없는 상태에서 심화기술까지 배우니까 습득하는 데 시간이 걸렸던 것 같다. 강의를 들으면서 정의, 순서나 방식에 대해서 감을 잡아갔고, 팀원들의 도움도 많이 받아 차근차근 풀어나갔다. 복습 시간을 갖게 되면서 속도가 다른 사람에 비해 느렸는데, 내가 담당한 부분은 최대한 내가 다 하고 싶어서 잠을 줄여가면서 다른 팀원들에게 피해주지 않으려고 했다. 하지만, 마감기한은 정해져 있고 프로젝트를 완성해야 하기에 메인페이지 일부와 Oauth 구글은 다른 팀원의 도움을 받아 같이 완성했다.
  - Oauth 카카오 로그인을 초반에 작업하면서 분명 강사님께 받은 코드와 검색을 통해서 틀린 부분이 없다는 것을 몇번이고 확인했는데, 경로에 '/' 유무, 오타 등으로 인해서 진행이 더뎠다. 다 완성하고 나니 조금만 더 꼼꼼히 봤다면 더 시간을 단축시킬 수 있었을 거라 생각이 들었고, 마음이 조급해서 더 눈에 잘 안 보였던 것 같다. 좀 더 차분히 여유를 가지고 천천히 코딩하는 습관을 만들어야겠다.

## 👍 ERD
![codefarmerERD](https://user-images.githubusercontent.com/109491137/210177806-342f8e93-a520-4b71-923a-d1fbe7ba6641.png)

## ✨ 포트폴리오 & Flow Chart
![spring포트폴리오_너와농부싶어_김지연_1](https://user-images.githubusercontent.com/109491137/210179710-fe7425c3-bc3b-4d68-8051-df7391eba115.png)
![spring포트폴리오_너와농부싶어_김지연_2](https://user-images.githubusercontent.com/109491137/210179712-17204450-cfee-42b7-b31b-2ca2e943f4a4.png)
![spring포트폴리오_너와농부싶어_김지연_3](https://user-images.githubusercontent.com/109491137/210179713-3b27835e-0fd0-42cc-8b62-a8bf2e4bedba.png)
![spring포트폴리오_너와농부싶어_김지연_4](https://user-images.githubusercontent.com/109491137/210179714-e8bfe766-27f3-47ce-9398-ac6b8a54d47e.png)
![spring포트폴리오_너와농부싶어_김지연_5](https://user-images.githubusercontent.com/109491137/210179715-6b5b0808-5fd0-40b9-a427-1ea5ac9c5a6d.png)
![spring포트폴리오_너와농부싶어_김지연_6](https://user-images.githubusercontent.com/109491137/210179716-c3b0be43-652c-4bdf-9b62-c53744cee85f.png)
![spring포트폴리오_너와농부싶어_김지연_7](https://user-images.githubusercontent.com/109491137/210179717-940ffef3-c3ce-4580-b5dc-923ee69c8ce6.png)
![spring포트폴리오_너와농부싶어_김지연_8](https://user-images.githubusercontent.com/109491137/210179718-fe196c83-71b9-44b7-abcc-61c7c1f9118e.png)
![spring포트폴리오_너와농부싶어_김지연_9](https://user-images.githubusercontent.com/109491137/210179720-146aec8e-71ff-49b0-afc0-5c0b0b7602a1.png)
![spring포트폴리오_너와농부싶어_김지연_10](https://user-images.githubusercontent.com/109491137/210179721-60db940c-9e63-4129-bd23-4431aad193ef.png)
![spring포트폴리오_너와농부싶어_김지연_11](https://user-images.githubusercontent.com/109491137/210179723-23c0cc46-8fd3-4c19-ab23-433767b082aa.png)
![spring포트폴리오_너와농부싶어_김지연_12](https://user-images.githubusercontent.com/109491137/210179724-ee88ab89-da39-4390-bcd9-146317782c3e.png)
![spring포트폴리오_너와농부싶어_김지연_13](https://user-images.githubusercontent.com/109491137/210179725-af8f29da-c564-4968-a958-c8d97ce967eb.png)
![spring포트폴리오_너와농부싶어_김지연_14](https://user-images.githubusercontent.com/109491137/210179726-d5988bc3-5a80-4740-b24d-7223b691900b.png)
![spring포트폴리오_너와농부싶어_김지연_15](https://user-images.githubusercontent.com/109491137/210179727-5bb06ad0-2fe0-460f-b80e-a7059483a3b2.png)
![spring포트폴리오_너와농부싶어_김지연_16](https://user-images.githubusercontent.com/109491137/210179729-97445d31-75f0-443a-8cb3-50039f6ba24a.png)
![spring포트폴리오_너와농부싶어_김지연_17](https://user-images.githubusercontent.com/109491137/210179731-9c16310d-8595-439a-99f7-6ca85e6c8efe.png)
![spring포트폴리오_너와농부싶어_김지연_18](https://user-images.githubusercontent.com/109491137/210179733-9c82a3b5-598a-4e7a-9c16-498eb0784482.png)
![spring포트폴리오_너와농부싶어_김지연_19](https://user-images.githubusercontent.com/109491137/210179734-93c14ec2-6d04-422b-aad9-3071c2908a48.png)
![spring포트폴리오_너와농부싶어_김지연_20](https://user-images.githubusercontent.com/109491137/210179735-7116288c-329a-45ed-9ad8-820bc87e0052.png)
![spring포트폴리오_너와농부싶어_김지연_21](https://user-images.githubusercontent.com/109491137/210179737-8553ec41-bf35-4009-978c-35c52c8715cd.png)
![spring포트폴리오_너와농부싶어_김지연_22](https://user-images.githubusercontent.com/109491137/210179739-fc3f6f6e-dd00-4a0c-aaf3-d6e0065c9a59.png)
![spring포트폴리오_너와농부싶어_김지연_23](https://user-images.githubusercontent.com/109491137/210179740-92d5956f-016a-4780-b075-1ea8a3a92aba.png)
![spring포트폴리오_너와농부싶어_김지연_24](https://user-images.githubusercontent.com/109491137/210179741-02e80019-2d88-448f-8cab-a5b2f5c575e7.png)
![spring포트폴리오_너와농부싶어_김지연_25](https://user-images.githubusercontent.com/109491137/210179742-a796df4d-c427-4d3c-93bf-d1d13b5f457c.png)
![spring포트폴리오_너와농부싶어_김지연_26](https://user-images.githubusercontent.com/109491137/210179743-159640e4-829f-482e-8ff4-22f907bb7eeb.png)
![spring포트폴리오_너와농부싶어_김지연_27](https://user-images.githubusercontent.com/109491137/210179745-6abc50fb-e1a2-47c6-86dd-a6df9e112923.png)
![spring포트폴리오_너와농부싶어_김지연_28](https://user-images.githubusercontent.com/109491137/210179748-7891a274-8bf1-4f91-b796-27c832018d71.png)
![spring포트폴리오_너와농부싶어_김지연_29](https://user-images.githubusercontent.com/109491137/210179749-09cdf55f-332c-4e3b-97ca-4cfc7fcbf833.png)
![spring포트폴리오_너와농부싶어_김지연_30](https://user-images.githubusercontent.com/109491137/210179751-cda57dab-b85f-4c6c-bf97-81fc76524339.png)
![spring포트폴리오_너와농부싶어_김지연_31](https://user-images.githubusercontent.com/109491137/210179752-1312a741-68c8-414c-b401-666a63347090.png)
![spring포트폴리오_너와농부싶어_김지연_32](https://user-images.githubusercontent.com/109491137/210179753-3b037414-8112-4b4d-bdf7-80f1b08f78d3.png)
![슬라이드0002](https://user-images.githubusercontent.com/109491137/210179764-2c5a9742-a74d-4f3b-913c-c29f06e52a85.jpg)
![슬라이드0003](https://user-images.githubusercontent.com/109491137/210179765-153bd142-9737-4c3d-b5cf-a48005e5656b.jpg)
![슬라이드0004](https://user-images.githubusercontent.com/109491137/210179766-16a5268c-5df6-4e66-aed8-93df390de820.jpg)
![슬라이드0005](https://user-images.githubusercontent.com/109491137/210179767-a2f557e7-9f55-44f5-bd56-bf7c37b88668.jpg)
![슬라이드0006](https://user-images.githubusercontent.com/109491137/210179768-af4a435f-9727-4f36-bbcf-36425259ca5b.jpg)
![슬라이드0007](https://user-images.githubusercontent.com/109491137/210179770-66fe89f1-3b06-46cf-9a86-d62e2abd37e8.jpg)
![슬라이드0008](https://user-images.githubusercontent.com/109491137/210179771-a7510aa3-695e-4f47-91fb-7cf9b39b881b.jpg)
![슬라이드0009](https://user-images.githubusercontent.com/109491137/210179772-90a6c6dc-26ef-4b6d-bfa2-c5c2679012e3.jpg)
![슬라이드0010](https://user-images.githubusercontent.com/109491137/210179773-17bdd6bc-85f4-4e7b-8dd9-8c3618594317.jpg)
![슬라이드0011](https://user-images.githubusercontent.com/109491137/210179774-51dccfdc-2905-4205-90b6-540e9e9d89c5.jpg)
![슬라이드0012](https://user-images.githubusercontent.com/109491137/210179776-c11bcf48-b9a1-4454-8f6d-5b512d70d199.jpg)
![슬라이드0013](https://user-images.githubusercontent.com/109491137/210179777-6b3b4208-8b53-4b0b-974c-6bb581c6618b.jpg)
![슬라이드0014](https://user-images.githubusercontent.com/109491137/210179778-77c97711-a766-4018-9a4a-029e4201fcf6.jpg)
![spring포트폴리오_너와농부싶어_김지연_33](https://user-images.githubusercontent.com/109491137/210179754-4a053127-b70f-4f1f-beb8-b2162fa92499.png)
