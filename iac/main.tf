terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.6.0"
    }
  }
}

provider "docker" {
  host = "npipe:////.//pipe//dockerDesktopLinuxEngine"
}

resource "docker_container" "fibonacci" {
  name  = var.container_name
  image = var.image_name

  env = [
    "APP_VERSION=terraform-local"
  ]

  ports {
    internal = 8080
    external = 8082
  }
}