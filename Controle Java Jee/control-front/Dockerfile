FROM node:16.18.1 As build

WORKDIR /app
COPY / ./
COPY package*.json ./

RUN npm install -g @angular/cli@14.2.9 && \
    npm install && \
    ng build
COPY . .

FROM nginx:1.17.1-alpine
WORKDIR /app
COPY --from=build /app/dist/controle_front /usr/share/nginx/html
