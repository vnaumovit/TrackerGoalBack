async function getMainModal() {
  $('#mainModal').modal({
    keyboard: true,
    backdrop: 'static',
    show: false
  }).on('show.bs.modal', (event) => {
    let thisModal = $(event.target);
    let id = thisModal.attr('data-id');
    let action = thisModal.attr('data-action');
    switch (action) {
      case 'editItem':
        editItem(thisModal, id);
        break;
      case 'deleteItem':
        deleteItem(thisModal, id);
        break;
      case 'editUser':
        editUser(thisModal, id);
        break;
      case 'deleteUser':
        deleteUser(thisModal, id);
        break;
    }
  }).on('hidden.bs.modal', (e) => {
    let thisModal = $(e.target);
    thisModal.find('.modal-title').html('');
    thisModal.find('.modal-body').html('');
    thisModal.find('.modal-footer').html('');
  })
}

async function getOptionalModal() {
  $('#modalOptional').modal({
    keyboard: true,
    backdrop: 'static',
    show: false
  }).on('show.bs.modal', (event) => {
    let thisModal = $(event.target);
    let id = thisModal.attr('data-id');
    let action = thisModal.attr('data-action');
    switch (action) {
      case 'addSize':
        addSize(thisModal)
        break;
      case 'editSize':
        editSize(thisModal, id);
        break;
      case 'addSale':
        addSale(thisModal);
        break
      case 'editSale':
        editSale(thisModal, id);
        break
    }
  }).on('hidden.bs.modal', (e) => {
    let thisModal = $(e.target);
    let count = $('#sizeCount')
    console.log(count)
    thisModal.find('.modal-title-optional').html('');
    thisModal.find('.modal-body-optional').html('');
    thisModal.find('.modal-footer-optional').html('');
    thisModal.find('.modal-body-optional').append(count)
  })
}

function shopOptionalModal(event) {
  let modalOptional = $('#modalOptional');
  let targetButton = $(event);
  console.log(targetButton)
  let id = targetButton.attr('data-id');
  let action = targetButton.attr('data-action');
  modalOptional.attr('data-id', id);
  let attr = modalOptional.attr('data-action', action);
  modalOptional.modal('show');

  if (attr === 'addSize') {
  sizes = [];
  }
}

function showMainModal(event) {
  let modalItem = $('#mainModal');
  let targetButton = $(event.target);
  let buttonItemId = targetButton.attr('data-id');
  let buttonAction = targetButton.attr('data-action');
  modalItem.attr('data-id', buttonItemId);
  modalItem.attr('data-action', buttonAction);
  modalItem.modal('show');
}

function fillSizeModal(modal, text) {
  modal.find('.modal-title-optional').html(text);
  let addButton = `<button class="btn btn-success ml-3" type="submit" id="sizeSubmit">Подтвердить</button>`;
  let groupButtons =  `<div class="btn-group ml-3" role="group">
    <button class="btn btn-secondary" type="button" id="addElseSize">Добавить еще размер</button>
    <button class="btn btn-secondary" type="button" id="deleteSizeButton">Удалить размер</button>
  </div>`
  let closeButton = `<button type="button" id="closeSize" class="btn btn-danger ml-3" data-dismiss="modal">Закрыть</button>`;
  modal.find('.modal-footer-optional').append(addButton);
  modal.find('.modal-footer-optional').append(groupButtons);
  modal.find('.modal-footer-optional').append(closeButton);
}

function fillMainModal(modal, text) {
  modal.find('.modal-title').html(text);
  let editButton = `<button class="btn btn-success btn-left" type="submit" id="editButton">Изменить</button>`;
  let closeButton = `<button type="button" id="closeButton" class="btn btn-secondary bl-5" data-dismiss="modal">Закрыть</button>`;
  modal.find('.modal-footer').append(editButton);
  modal.find('.modal-footer').append(closeButton);
}
