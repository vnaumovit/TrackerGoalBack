async function deleteUser(modal, id) {
  let request = await userFetch.findOneUser(id);
  let user = await request.json();
  fillMainModal(modal, 'Удаление пользователя');
  deleteUserForm(user, modal);
  $('#deleteButton').on('click', async () => {
    const response = await userFetch.deleteUser(id);
    if (response.ok) {
      getUsers();
      modal.modal('hide');
      alertMessage('Пользователь успешно удален')
    }
  })

}

function deleteUserForm(user, modal) {
  let bodyForm = `
            <form class="form-group text-center" id="deleteUser">
               <div class="form-group">
                    <label for="id" class="col-form-label">ID</label>
                    <input type="text" class="form-control username" id="id" value="${user.id}" readonly>
               </div>
                   
               <div class="form-group">
                    <label for="username" class="col-form-label">Никнейм</label>
                    <input type="text" class="form-control username" id="username" value="${user.username}" readonly>
               </div>

                <div class="form-group">
                    <label for="name" class="com-form-label">Имя</label>
                    <input type="text" class="form-control" id="name" value="${user.name}" readonly>
                </div>

                <div class="form-group">
                    <label for="surname" class="com-form-label">Фамилия</label>
                    <input type="text" class="form-control" id="surname" value="${user.surname}" readonly>
                </div>

                <div class="form-group">
                    <label for="age" class="com-form-label">Возраст</label>
                    <input type="number" class="form-control" id="age" value="${user.age}" readonly>
                </div>
                
                <div class="form-group">
                    <label for="gender" class="com-form-label">Пол</label>
                    <input type="text" class="form-control" id="gender" value="${user.gender}" readonly>
                </div>

                <div class="form-group">
                    <label for="numberPhone" class="com-form-label">Номер</label>
                    <input type="tel" class="form-control" id="numberPhone" value="${user.numberPhone}"  readonly>
                </div>
                
                 <div class="form-group">
                <label for="roles" class="com-form-label">Роли:</label>
                <select id="roles" class="form-control select" size="2" name="roles" style="max-height: 100px" disabled>
                <option>${user.roles.map(role => ' ' + role.name.substring(5))}</option>
                </select>
            </div>

            </form>
        `;
  modal.find('.modal-body').append(bodyForm);
}
