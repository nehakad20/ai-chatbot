# AI Chatbot (Spring Boot)

Ready-to-run Spring Boot project that demonstrates a simple AI chatbot backed by OpenAI's Chat Completions API.

## Quick setup

1. Install Java 17+ and Maven.
2. Create PostgreSQL database `chatdb` and adjust `src/main/resources/application.properties` for username/password.
3. Set OpenAI API key in environment:
   - Linux/macOS: `export OPENAI_API_KEY="sk-..."`
   - Windows PowerShell: `$env:OPENAI_API_KEY = "sk-..."`
4. Build & run:
   ```bash
   mvn clean package
   java -jar target/ai-chatbot-0.0.1-SNAPSHOT.jar
   ```
5. Open `http://localhost:8080/` in your browser.

## Notes
- The project uses `gpt-3.5-turbo` model by default. Change model or add conversation history in `OpenAIService`.
- If OpenAI key is not set, the service returns a helpful message in the bot response.
