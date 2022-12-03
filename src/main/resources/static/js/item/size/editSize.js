async function editSize(modal, itemId) {
  let request = await sizeFetch.getSizesByItemId(itemId);
  let editSizes = await request.json().then(sizes => sizes);
  fillSizeModal(modal, 'Изменение размеров');
  addSizeBaseForm(modal)
  let count = $('#sizeCount').val();
  let checkSizes = ((count === '' || count === '0') && editSizes.length !== 0)
  if (checkSizes) {
    await editSizeForm(editSizes, modal);
  } else {
    await fillOldSizes(modal);
  }
  await addOrDeleteSize(modal);
  let button = '#sizeSubmit';
  validation(button);
  $(button).on('click', async () => {
    await fillSizes(modal, itemId);
  })
  $('#closeSize').on('click', async () => {
    await fillSizes(modal, itemId);
  })
  $('#closeSizeSecond').on('click', async () => {
    await fillSizes(modal, itemId);
  })
}

async function editSizeForm(sizes, modal) {
    let sizesForm = await sizesForeach(sizes, 0);
    let form = document.querySelector('#addSizeForm');
    this.sizes = sizes
    modal.find(form).append(sizesForm);
}