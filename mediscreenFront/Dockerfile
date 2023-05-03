# Stage 1: build the Angular app
FROM node:18.13.0 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: serve the app with Nginx
FROM nginx:1.21-alpine
COPY --from=build /app/dist/mediscreen-front /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]