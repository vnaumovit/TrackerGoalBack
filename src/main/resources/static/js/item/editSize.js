async function editSize(modal, itemId) {
  let request = await sizeFetch.getSizesByItemId(itemId);
  let editSizes = request.json();
  fillSizeModal(modal, 'Изменение размеров');
  addSizeBaseForm(modal)
  let count = await editSizeForm(editSizes, modal, 0);
  count = await addOrDeleteSize(modal, count);
  let button = '#sizeSubmit';
  validation(button);
  $(button).on('click', async () => {
    let count = $('#sizeCount').val();
    console.log(count)
    sizes = await sizesData(modal, itemId, count)
    modal.modal('hide');
  });
}

async function editSizeForm(sizes, modal, count) {
  let sizesForm = ''
  await sizes.then(sizes => {
     sizes.forEach(s => {
      count++;
      let size = `size${count}`
      let quantity = `quantity${count}`
      sizesForm += `
        <div class="form-floating mb-3" id="sizeForm${count}">
            <input hidden id="id${count}" value="${s.id}">
            <label class="col-form-label">Размер ${count}</label>
            <input class="form-control" id="${size}" required min="0" value="${s.size}">
            <label class="col-form-label">Количество ${count}</label>
            <input class="form-control" id="${quantity}" required min="0" value="${s.quantity}">
            <div class="invalid-feedback">Добавьте размеры</div>
            <div class="valid-feedback">Все хорошо!</div>
        </div>`
    })
    let form = document.querySelector('#addSizeForm');
    modal.find(form).append(sizesForm);
  })
  return count;
}
