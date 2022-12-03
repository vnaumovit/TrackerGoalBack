async function createItem() {
  let button = '#addItem'
  validation(button);
  $('#addItem').click(async () => {
    let addItemForm = $('#addItemForm')
    let data = await itemData(addItemForm);
    const response = await itemFetch.addItem(data);
    if (response.ok) {
      sizes = [];
      clearItemFields(addItemForm);
      clearSizes()
      addItemForm.prepend(alertMessage('Товар успешно добавлен!'));
      await getItems(true);
      $('.nav-tabs a[href="#itemTable"]').tab('show');
    }
  });
}

function clearItemFields(addItemForm) {
  addItemForm.find('#name').val('');
  addItemForm.find('#number').val('');
  addItemForm.find('#number').val('');
  addItemForm.find('#addSize').val('');
  addItemForm.find('#image').val('');
}

async function getNewItemForm() {
  let button = $(`#addItem`);
  let form = $(`#addItemForm`)
  button.click(() => {
    form.show()
  })
}

