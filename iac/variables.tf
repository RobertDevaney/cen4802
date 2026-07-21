variable "container_name" {
  description = "Name of the Fibonacci Docker container managed by Terraform."
  type        = string
  default     = "fibonacci-iac"
}

variable "image_name" {
  description = "Docker image used for the Fibonacci application."
  type        = string
  default     = "cen4802-fibonacci:monitor"
}