document.addEventListener('DOMContentLoaded', function() {
    // DOM 요소 선택
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const messageList = document.getElementById('messageList');

    // 메시지 전송 함수
    function sendMessage() {
        const message = messageInput.value.trim();
        if (message) {
            // 새로운 메시지 요소 생성
            const messageElement = document.createElement('div');
            messageElement.className = 'message';
            messageElement.textContent = message;
            
            // 메시지 목록에 추가
            messageList.appendChild(messageElement);
            
            // 입력 필드 초기화
            messageInput.value = '';
            
            // 스크롤을 최신 메시지로 이동
            messageList.scrollTop = messageList.scrollHeight;
        }
    }

    // 이벤트 리스너 등록
    sendButton.addEventListener('click', sendMessage);
    messageInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });
}); 