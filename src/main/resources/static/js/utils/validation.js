// Пример стартового JavaScript для отключения отправки форм при наличии недопустимых полей
function validation(button) {
  'use strict'

  // Получите все формы, к которым мы хотим применить пользовательские стили проверки Bootstrap
  var forms = document.querySelectorAll('.needs-validation')

  // Зацикливайтесь на них и предотвращайте отправку
  $(button).click(async (event) => {
    forms.forEach(form => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
}

function alertMessage(text) {
  return `<div class="alert alert-success alert-dismissible fade show col-12" role="alert" id="successMessage">
          ${text}
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
              </button>
          </div>`;
}