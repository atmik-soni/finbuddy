// File: src/main/resources/static/responseHandler.js

async function fetchAndAggregateResponse(url, requestBody) {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestBody)
        });

        if (!response.body) {
            throw new Error('No response body found');
        }

        const reader = response.body.getReader();
        let aggregatedResponse = '';
        const decoder = new TextDecoder();

        while (true) {
            const { done, value } = await reader.read();
            if (done) break;
            aggregatedResponse += decoder.decode(value);
        }

        console.log('Aggregated Response:', aggregatedResponse);
        return aggregatedResponse;
    } catch (error) {
        console.error('Error fetching and aggregating response:', error);
        throw error;
    }
}

// Example usage
const requestBody = {
    model: 'llama3.2',
    prompt: 'What is the capital of France?'
};

fetchAndAggregateResponse('http://localhost:8081/api/ollama/ask', requestBody)
    .then(response => console.log('Final Response:', response))
    .catch(error => console.error('Error:', error));