# cp2-ddd-java

Sistema de Logística para Entregas (E-commerce) — CP2 Domain Driven Design / POO.

## Java (SDKMAN)

O arquivo [`.sdkmanrc`](.sdkmanrc) fixa o JDK deste projeto. Com [SDKMAN](https://sdkman.io/) instalado, na raiz do repositório:

```bash
sdk env
java -version
```

Se essa distribuição ainda não estiver instalada:

```bash
sdk install java 21.0.2-open
sdk env
```

## Como executar (Java puro, sem Maven)

Na raiz do repositório:

```bash
mkdir -p out
find src/main/java -name "*.java" -print0 | xargs -0 javac -d out -encoding UTF-8
java -cp out com.cp2.logistica.Main
```

Ou abrir o projeto na IDE com source root em `src/main/java` e executar `com.cp2.logistica.Main`.
