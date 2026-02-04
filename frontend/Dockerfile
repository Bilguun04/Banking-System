# Stage 1: Build Angular application
FROM node:21-alpine AS builder

WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm ci

# Copy the Angular project files
COPY . .

# Build the Angular application for production
RUN npm run build

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Copy the built application from the builder stage
COPY --from=builder /app/dist/src /usr/share/nginx/html

# Copy custom Nginx configuration (optional)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
