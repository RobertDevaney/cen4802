output "container_name" {
  description = "Name of the Docker container created by Terraform."
  value       = docker_container.fibonacci.name
}

output "container_id" {
  description = "ID of the Docker container created by Terraform."
  value       = docker_container.fibonacci.id
}