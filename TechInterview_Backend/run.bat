Rem install node moduls
call npm install express  
call npm install cookie-parser 
call npm install client-sessions
call npm install mysql

cls 
 
REM start node  server 

echo Node server running
call node server.js
