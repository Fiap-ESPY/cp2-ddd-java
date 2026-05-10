# cp2-ddd-java

**Sistema de Logística para Entregas (E-commerce)** — CP2 **Domain Driven Design** / **POO** (FIAP / 2ESPY).

Simular um serviço de logística de entregas (inspirado em marketplaces e e-commerce), com foco em **modelagem orientada a objetos**: entregadores com comportamentos distintos, entregas com status, atribuição e acompanhamento do fluxo operacional.

## Integrantes do Grupo

- Beatriz Cortez — 561431
- Bruno Alves — 563986
- Gabriel Augusto — 564126
- Davi de Jesus — 566316


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

## Como executar

O código usa sintaxe compatível com **Java 21+** (por exemplo *text blocks* e *switch* com setas). Na raiz, use o JDK aplicado pelo passo anterior:

```bash
sdk env
mkdir -p out
find src -name "*.java" -print0 | xargs -0 javac -d out -encoding UTF-8
java -cp out com.cp2.logistica.Main
```

Ou abrir o projeto na IDE com **source root** em `src` e executar **`com.cp2.logistica.Main`**.

## Explicação do sistema

A aplicação roda por **`Main`** → **`MenuConsole`** → **`SistemaDelivery`**, com entrada via **`Scanner`**. O domínio fica em **`com.cp2.logistica.domain`**.

Funcionalidades principais:

| Opção | Ação |
|------:|------|
| **1** | Cadastrar **entregador** (tipo: bicicleta, moto ou carro) |
| **2** | **Criar entrega** com endereço e distância (km)|
| **3** | **Associar** entregador disponível a uma entrega **PENDENTE**; exibe tempo estimado (minutos) e valor estimado (tarifa) |
| **4** | **Listar** entregas |
| **5** | **Atualizar status** da entrega (respeitando transições válidas no domínio) |
| **0** | Sair |


---

## Diagrama de Classes UML

Inclua a imagem do diagrama (por exemplo em `assets/diagrama_UML.png`) e referencie aqui:

```markdown
![Diagrama UML](assets/diagrama_UML.png)
```

*(Adicione o arquivo e descomente/ajuste a linha acima na entrega.)*

## Perguntas discursivas

1. **Herança:** como foi utilizada no sistema? Qual problema resolveu e quais classes estão envolvidas?
2. **Interfaces:** qual interface foi criada? Por que foi utilizada e qual vantagem trouxe?
3. **Classe abstrata:** qual o papel da classe abstrata? Por que não poderia ser uma classe “comum”?
