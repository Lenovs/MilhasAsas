# ✈️ MilhasAsas

**MilhasAsas** é uma plataforma de intermediação de milhas e serviços de viagem. Usuários podem comprar milhas, trocar por passagens aéreas, reservar hospedagens e alugar veículos — tudo integrado com fornecedores parceiros e gerenciado pela plataforma.

---

## 🚀 Objetivo

Criar um sistema completo em **Java com Spring Boot** que permita:

- Compra e venda de milhas com companhias aéreas parceiras
- Troca de milhas por passagens, hospedagens e veículos
- Gestão de usuários, fornecedores e ofertas
- Intermediação centralizada pela plataforma com controle de taxas e transações

---

## 🧱 Estrutura do Projeto
     MilhasAsas/
        └── src/
          └── main/
            └── java/
              └── com/
                └── Milhas/
                   ├── model/         # Entidades JPA
                   ├── repository/    # Interfaces JpaRepository 
                   ├── service/       # Regras de negócio
                   └── controller/    # APIs REST


---
# RELAÇÕES DE CLASSES
[![](https://mermaid.ink/img/pako:eNq9V91O2zAUfpXIElcrCFhLQy4mVbTSKgFjLXAx9eYQn6bWHLtznIof9XpPsCfixWY3Ses06VpgUCE1Ob_f-exzTnkioaRIAhJySJIug0hBPBKe-ezteb3L63630-0NvatB__Ksf9XpDzPlwty7SVJQTHpPmdB-Pp1LEXmMOpKhVszIhIyxKsUYGK-KExQTqIrD6dgRMqG9BDiVF4xPIHE0VKZ3HDPlACFPMB8JF_0VBw1jqWJ4VQF5Dg33MEQ1Y6GszXIm4ymICYMOKnxxprVgt3JHsqViEcZVOcVEMyFLIULgXdDoUUNHtb6pwtDhcEn8Qu4SvwZ1gIlhBbYjLgPI3ao4ZsCluoJIruGIFxBuEqAbgHyVGvkbef9u7rnekXrNph_I7y7VvTfDNj6V6s2XG1mY8h1Zjs3Y4rJmQpgyI3P74QOv-E643_sQvo1RacjQboFi4_1KQWhGgeIGPm5RUBffnZQcQXiUJVMp2Azrh-q1ApFACPK_IKljo-40lyDM0hr0zjvPv5__mK1l9tegt9pimUmxtEbkaES8_f0v5ikG8WBe1sEHnhnZnD3CVk9n2lkn53C3O2Ut_GK34toFHvA0goIBZ6vtVKE5AlQx0qJj_u1fqvNVrkW1r8u7rNl1zwKsLdqaIBlqaRoFQ9zVqdRXgTczbYFFShvQGpejZDqHKWtivmvl-TnX6laUFAkz8mpQ5gsq8KYySVKWWedCa7hwLCXJQlldZrZBWYdvpa0iXO6COv6Xp5cPkTu2aq5CaT2KIGVWHIP8eaO-llVHX8VdOuZNZ7rePdbOddxsVQJUZ7BCRBokUoySYAw8wQaJzT0H-04Ws3RE9ATNBiWBeaSgfo7ISMyNk0H7Q8qYBFqlxk3JNJoUL-nUzErMf94vIyt7l9WZTIUmgX-8CEGCJ3JPgmazfdA6-nzo-6eH7aZ_0m41yIMR-wfHzdPTtt82fyd-66g1b5DHRdbDA98amY7UUl3k_1TYr_lfxUPOng?type=png)](https://mermaid.live/edit#pako:eNq9V91O2zAUfpXIElcrCFhLQy4mVbTSKgFjLXAx9eYQn6bWHLtznIof9XpPsCfixWY3Ses06VpgUCE1Ob_f-exzTnkioaRIAhJySJIug0hBPBKe-ezteb3L63630-0NvatB__Ksf9XpDzPlwty7SVJQTHpPmdB-Pp1LEXmMOpKhVszIhIyxKsUYGK-KExQTqIrD6dgRMqG9BDiVF4xPIHE0VKZ3HDPlACFPMB8JF_0VBw1jqWJ4VQF5Dg33MEQ1Y6GszXIm4ymICYMOKnxxprVgt3JHsqViEcZVOcVEMyFLIULgXdDoUUNHtb6pwtDhcEn8Qu4SvwZ1gIlhBbYjLgPI3ao4ZsCluoJIruGIFxBuEqAbgHyVGvkbef9u7rnekXrNph_I7y7VvTfDNj6V6s2XG1mY8h1Zjs3Y4rJmQpgyI3P74QOv-E643_sQvo1RacjQboFi4_1KQWhGgeIGPm5RUBffnZQcQXiUJVMp2Azrh-q1ApFACPK_IKljo-40lyDM0hr0zjvPv5__mK1l9tegt9pimUmxtEbkaES8_f0v5ikG8WBe1sEHnhnZnD3CVk9n2lkn53C3O2Ut_GK34toFHvA0goIBZ6vtVKE5AlQx0qJj_u1fqvNVrkW1r8u7rNl1zwKsLdqaIBlqaRoFQ9zVqdRXgTczbYFFShvQGpejZDqHKWtivmvl-TnX6laUFAkz8mpQ5gsq8KYySVKWWedCa7hwLCXJQlldZrZBWYdvpa0iXO6COv6Xp5cPkTu2aq5CaT2KIGVWHIP8eaO-llVHX8VdOuZNZ7rePdbOddxsVQJUZ7BCRBokUoySYAw8wQaJzT0H-04Ws3RE9ATNBiWBeaSgfo7ISMyNk0H7Q8qYBFqlxk3JNJoUL-nUzErMf94vIyt7l9WZTIUmgX-8CEGCJ3JPgmazfdA6-nzo-6eH7aZ_0m41yIMR-wfHzdPTtt82fyd-66g1b5DHRdbDA98amY7UUl3k_1TYr_lfxUPOng)




## ⚙️ Tecnologias e Dependências

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Web**
- **Lombok**
- **H2 Database** (para testes locais)
- **PostgreSQL** (para produção)
- **JUnit 5** (testes unitários)

### 🔧 Plugins configurados

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.5.4'
    id 'io.spring.dependency-management' version '1.1.7'
}

📦 Dependências principais
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'org.postgresql:postgresql'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

