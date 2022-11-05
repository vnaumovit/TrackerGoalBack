$(async function (){
 await registration()
})

function registration() {
  let button = '#registrationButton';
  validation(button)

  $(button).click(async () => {
    let registrationForm = $('#registrationForm')
    let username = registrationForm.find('#username').val().trim();
    let password = registrationForm.find('#password').val().trim();
    let name = registrationForm.find('#name').val().trim();
    let surname = registrationForm.find('#surname').val().trim();
    let phone = registrationForm.find('#phone').val().trim();
    let gender = registrationForm.find('#gender').val();
    let age = registrationForm.find('#age').val();

    let data = {
      username: username,
      password: password,
      name: name,
      surname: surname,
      phone: phone,
      gender: gender,
      age: age
    };

    async function registration(user) {
      return await fetch('/registration', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Referer': null
        },
        body: JSON.stringify(user)
      });
    }

    const response = await registration(data);
    console.log(response.status)
    if (response.ok) {
      alertSuccess(registrationForm);
    } else {
      alertError(registrationForm)
    }
  });

  function alertSuccess(registrationForm) {
    let alert = `<div class="alert alert-success alert-dismissible fade show col-12" role="alert">
                         Пользователь успешно зарегистрирован
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>`;
    registrationForm.prepend(alert);
  }
  function alertError(registrationForm) {
    let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert">
                         Пользователь с таким никнеймом уже зарегистрирован
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>`;
    registrationForm.prepend(alert);
  }
}

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
    }

  )
}
