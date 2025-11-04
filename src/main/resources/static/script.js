document.getElementById("send-btn").addEventListener("click", sendMessage);
document.getElementById("user-input").addEventListener("keypress", function (e) {
    if (e.key === "Enter") sendMessage();
});

async function sendMessage() {
    const input = document.getElementById("user-input");
    const chatBox = document.getElementById("chat-box");
    const userMessage = input.value.trim();
    if (!userMessage) return;

    // Display user message
    appendMessage(userMessage, "user-message");
    input.value = "";

    // Add typing animation
    const typingDiv = document.createElement("div");
    typingDiv.classList.add("bot-message", "typing");
    typingDiv.innerText = "Typing...";
    chatBox.appendChild(typingDiv);
    chatBox.scrollTop = chatBox.scrollHeight;

    try {
        const response = await fetch("/api/chat", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({message: userMessage})
        });
        const data = await response.json();

        // Remove typing animation
        chatBox.removeChild(typingDiv);

      appendMessage(data.botResponse || "No response from bot", "bot-message");
    } catch (error) {
        chatBox.removeChild(typingDiv);
        appendMessage("Error connecting to bot 😞", "bot-message");
    }
}

function appendMessage(message, className) {
    const chatBox = document.getElementById("chat-box");
    const msgDiv = document.createElement("div");
    msgDiv.classList.add("message", className);
    msgDiv.innerText = message;
    chatBox.appendChild(msgDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}
