# board
21-1 학습동아리 컴갤러들입니다    
간단한 git 사용법과 글쓰기 게시판을 구현하려고 합니다.
    

# 개발환경 
    
IDEA : intelliJ ultimate 없을시 community 버전이 있으니 참조.. https://noobnim.tistory.com/19    
DB : MySQL -> MariaDB (학교 전공에서 MariaDB를 쓰게되었는데 왠지 모르게 오류나서 MySQL에서 MariaDB로 변경)        
Framework : Spring Boot    
Template Engine : Thymeleaf    
    
# MariaDB 계정 설정 및 권한 부여
cmd를 관리자 권한으로 열고 아래와 같이 침    
    
    mysql -u root -p    
    
    MariaDB[(none)]> create database example; // example라는 데이터베이스를 생성합니다.    
    MariaDB[(none)]> create user 'user'@'%' identified by '1234'; 
    // user라는 사용자를 생성하고 비밀번호는 '1234'로 합니다. 개인적으로 1234인지 '1234'인지는 잘 기억이안나는데 한번해보시길...    
    MariaDB[(none)]> grant all on example.* to 'user'@'%'; // user 사용자에게 example 데이터베이스의 모든 권한을 줍니다.    
    MariaDB[(none)]> show databases; -- 현재 데이터베이스들을 볼 수 있습니다.    
    
IntelliJ에서 DB연동을 하고 @Entity를 붙여주게 되면 모델을 만들어주기 때문에 직접적으로 DB에서 쿼리를 작성하지 않아도 됩니다. - JPA를 검색하십시요    
    
    
# DB연동 설정
application.properties 파일에     
    
    server.port={포트는 기본적으로 8080}    
    spring.jpa.hibernate.ddl-auto=update    
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver    
    spring.datasource.url=jdbc:mariadb://localhost:3307/{자신이 만든 데이터베이스}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8    
    // 위의 MariaDB계정 설정 부분에서 데이터베이스 이름은 example로 했기 때문에 아시죠?     
    spring.datasource.username={자신이 설정한 username}    
    // 위의 MariaDB계정 설정 부분에서 username은 user로 했기 때문에 아시죠?     
    spring.datasource.password={자신이 설정한 비밀번호}     
    // 위의 MariaDB계정 설정 부분에서 password는 1234로 했기 때문에 아시죠?     
    
# 개발환경 설정
    
build.gradle 파일에     
    
sourceCompatibility = '1.8' // 개인적으로 각자 환경이 13인 경우도 있었기 때문에 그건 자기가 쓰는 자바 jdk 버전에 맞춰서..    
    
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'    
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'    
    implementation 'org.springframework.boot:spring-boot-starter-web'    
    compileOnly 'org.projectlombok:lombok'    
    //    runtimeOnly 'mysql:mysql-connector-java'    
    implementation 'org.mariadb.jdbc:mariadb-java-client:2.7.3'    
    implementation 'org.springframework.boot:spring-boot-devtools:2.4.4'    

    annotationProcessor 'org.projectlombok:lombok'    
    testImplementation 'org.springframework.boot:spring-boot-starter-test'    
    testImplementation ('org.springframework.boot:spring-boot-starter-test') {    
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'    
    }    
    runtimeOnly 'org.webjars:bootstrap:4.5.3'    
 
# 편의 설정
    
devtools에 대해서 검색하면 뭐하는앤지 나옴    
IntelliJ에는 기능을 많이 제공하기 때문에 cmd에서 mariadb로 들어가지 않고 우측 상단에 database라는 탭이 있음  
그것을 이용하면 좀 더 편리함
    

------------------------------------------------------------------------------------------------------------------------    
# board.id, file.id의 index가 점핑되는 최악의 버그 발생

board entity, file entity를 join column을 안해줘서 그런지 오류가 발생하여    
board + file 합침... 원래 그러면 안되지만 지금은 다른 것도 해야되기 때문에 추후에     
고치는걸로...     
