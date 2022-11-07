async function createItem() {
  let button = '#addItem'
  validation(button);
  $('#addItem').click(async () => {
    let addItemForm = $('#addItemForm')
    let data = await itemData(addItemForm);
    console.log(data)
    const response = await itemFetch.addItem(data);
    if (response.ok) {
      clearItemFields(addItemForm);
      sizes = [];
      addItemForm.prepend(alertMessage('Товар успешно добавлен!'));
      await getItems();
      $('.nav-tabs a[href="#itemTable"]').tab('show');
    }
  });
}

function clearItemFields(addItemForm) {
  addItemForm.find('#name').val('');
  addItemForm.find('#itemType').val('');
  addItemForm.find('#number').val('');
  addItemForm.find('#number').val('');
  addItemForm.find('#addSize').val('');
  addItemForm.find('#image').val('');
}

