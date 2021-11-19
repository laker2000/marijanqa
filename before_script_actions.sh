#npm install -g npm@latest
npm install -g json-server
json-server --watch db.json 2>&1 | tee test.config &