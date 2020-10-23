# demoapp

Assalomu alaykum.

Web appni build qilish uchun PostgreSQL serverda `demoapp` nomli database bo'lishi kerak. 
Bu bazaga username: `postgres` password: `postgres` bo'lgan read/write access berilishi kerak. 
Agar qandaydir sabab bilan buni iloji bo'lmasa `src/main/resources/application.properties` fayldagi 
`spring.datasource.username=postgres`
`spring.datasource.password=postgres`
qiymatlari o'zgartiriladi. Project birinchi marta run bo'layotganida Liquibase orqali kerakli tablelarni migrate qiladi.

Project run bo'lganida http://localhost:8080/ link orqali kiriladi. Login/parol: admin/admin.
