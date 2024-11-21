# testeOllama
Programa em java utilizado para testes de modelos de IA do Ollama, os modelos testados foram os modelos de linguagem e de visão Llava e o Minicpm-v.

## Resultados

 ### 1º teste medelo Llava:
  O modelo apresentou grande imprecisão na extração das informações, mesmo mudando o prompt ele nao foi capaz de retornar nenhuma vez os dados corretos.
  Em 5 teste com prompts diferentes, ele retornou os dados no formato esperado em apenas 3, e mesmo assim com grande imprecisão. 
  
  Na primeira tentativa, ele nao foi capaz de reconhecer se a foto era de um RG Brasileiro ou um Passaporte e nas duas vezes que retornou os numeros do CPF, foi retornado dados complatamente incoerentes com os apresentados na imagem. 

### 2º teste modelo Minicpm-v:
 O modelo apresentou grande precisão e velocidade ao responder prompts simpples.
 Em 5 testes, ele errou apenas quando foi enviado um prompt mais generico, os prompts mais especificos continham uma ótima precisão.
