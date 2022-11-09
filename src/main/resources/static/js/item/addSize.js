let sizes = [];
async function addSize(modal) {
  fillSizeModal(modal, 'Добавление размеров');
  addSizeBaseForm(modal)
  await fillOldSizes(modal);
  await addOrDeleteSize(modal);
  let button = '#sizeSubmit';
  validation(button);
  $(button).on('click', async () => {
    await fillSizes(modal, null);
  })
  $('#closeSize').on('click', async () => {
    await fillSizes(modal, null);
  })
  $('#closeSizeSecond').on('click', async () => {
    await fillSizes(modal, null);
  })
  sizes = [];
}

async function fillSizes(modal, itemId) {
  let count = $('#sizeCount').val();
  sizes = await sizesData(modal, itemId, count);
  modal.modal('hide');
}