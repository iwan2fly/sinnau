import React, { useState } from 'react';

function Welcome() {
  const [username, setUsername] = useState('');
  const [showWelcome, setShowWelcome] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (username.trim()) {
      setShowWelcome(true);
    }
  };

  const handleReset = () => {
    setUsername('');
    setShowWelcome(false);
  };

  return (
    <div className="welcome-container">
      {!showWelcome ? (
        <>
          <h2>신나유니버스</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="username">사용자 이름</label>
              <input
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="이름을 입력하세요"
                required
              />
            </div>
            <button type="submit">입장하기</button>
          </form>
        </>
      ) : (
        <div className="welcome-message">
          <h2>안녕하세요 '{username}' 님,</h2>
          <p>신나유니버스에 오신 것을 환영합니다!</p>
          <button onClick={handleReset}>다시 입력하기</button>
        </div>
      )}
    </div>
  );
}

export default Welcome;