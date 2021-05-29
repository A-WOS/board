# board
21-1 학습동아리 컴갤러들입니다    
간단한 git 사용법과 글쓰기 게시판을 구현하려고 합니다.


# 개발환경 
intelliJ ultimate    
MySQL -> MariaDB (학교 전공에서 MariaDB를 쓰게되었는데 왠지 모르게 오류나서 MySQL에서 MariaDB로 변경)    
Spring Boot, Thymeleaf    

# DB연동 설정
application.properties 파일에     

server.port={포트는 기본적으로 8080}    
spring.jpa.hibernate.ddl-auto=update    
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver    
spring.datasource.url=jdbc:mariadb://localhost:3307/{자신이 만든 데이터베이스}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8    
spring.datasource.username={자신이 설정한 username}    
spring.datasource.password={자신이 설정한 비밀번호}    

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
 
 ------------------------------------------------------------------------------------------------------------------------    
# board.id, file.id의 index가 점핑되는 최악의 버그 발생

board entity, file entity를 join column을 안해줘서 그런지 오류가 발생하여    
board + file 합침... 원래 그러면 안되지만 지금은 다른 것도 해야되기 때문에 추후에     
고치는걸로...     
