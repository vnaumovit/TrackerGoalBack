let sizes = [];

async function addSize(modal) {
  let count = 0;
  fillSizeModal(modal, 'Добавление размеров');
  addSizeBaseForm(modal)
  count = await addSizeForm(modal, count);
  count = await addOrDeleteSize(modal, count);
  let button = '#sizeSubmit';
  validation(button);
  $(button).on('click', async () => {
    sizes = await sizesData(modal, null, count);
    modal.modal('hide');
  })
}
