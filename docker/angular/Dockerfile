FROM node:latest as node
WORKDIR /app
COPY src/main/webapp/ .
RUN npm install
RUN npm run build

FROM nginx:1.25.3-alpine3.18
COPY docker/angular/nginx.conf /etc/nginx/nginx.conf
COPY --from=node /app/dist/app /usr/share/nginx/html