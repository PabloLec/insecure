FROM node:latest as node
WORKDIR /app
COPY src/main/webapp/ .
RUN npm install
RUN npm run build

FROM nginx:alpine
COPY --from=node /app/dist/app /usr/share/nginx/html