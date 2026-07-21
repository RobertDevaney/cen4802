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

  command = [
    "sh",
    "-c",
    "while true; do java -cp app.jar com.cen4802.FibonacciApp; sleep 5; done"
  ]
}