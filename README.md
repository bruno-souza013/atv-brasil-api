# BrasilAPI - Consulta de CEP (Ktor)

Este repositório contém um app Android minimalista em Kotlin que consulta informações de endereço a partir de um CEP usando um cliente HTTP implementado com Ktor.

O objetivo desta atividade é demonstrar integração com APIs REST em um app Compose, arquitetura simples com ViewModel e exibição dos resultados em um popup (AlertDialog).

## Principais funcionalidades
- Entrada de CEP (apenas dígitos, máximo 8 caracteres)
- Consulta de CEP via API (cliente Ktor)
- Resultado exibido em um modal (AlertDialog) com botão "Fechar"
- Tratamento de estados: carregando, erro e sucesso

## Estrutura principal relevante
- `app/src/main/java/com/fatec/brasilapi/ui/cep/CepScreen.kt` — UI em Jetpack Compose. Campo de entrada, botão Buscar e AlertDialog para exibir o resultado.
- `app/src/main/java/com/fatec/brasilapi/ui/cep/CepViewModel.kt` — ViewModel que controla o estado da tela, executa a busca e provê um `dismiss()` para fechar o popup.
- `app/src/main/java/com/fatec/brasilapi/MainActivity.kt` — Activity que inicia a tela `CepScreen` dentro do `BrasilAPITheme`.

## Requisitos
- Android Studio (recomendado) ou SDK Android + Gradle
- JDK 11 ou superior
- Dispositivo físico ou emulador Android com API compatível

## Como rodar

1. Simplesmente execute o app a partir do Android Studio (Run ▶︎).

## Como usar o app
1. Digite um CEP válido de 8 dígitos (apenas números).
2. Pressione "Buscar" (ou use a ação Search no teclado).
3. Se a consulta for bem-sucedida, um popup exibirá as informações do endereço (CEP, estado, cidade, bairro e rua). Toque em "Fechar" para descartá-lo.
4. Se ocorrer um erro, a mensagem de erro será exibida na tela.

Exemplo de CEP para testar: `11900000` (CEP de referência)

