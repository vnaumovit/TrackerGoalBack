async function callFilter() {
  $('#btn-on').click(function () {
    $('body input:checkbox').prop('checked', true);
  });
  $('#btn-off').click(function () {
    $('body input:checkbox').prop('checked', false);
  });
  await applyFilter();
  await resetFilter();
}
async function applyFilter() {
  await $('#filterApply').click(async () => {
    let sizes = []
    $('body input:checkbox:checked').each(function () {
      sizes.push($(this).val())
    });
    let isInStock = $('body input:radio:checked').val();
    let itemType = $('#itemTypeFilter').val();
    if (itemType === 'Все') {
      itemType = null
    }
    let pageDto = { pageNumber: 0, pageSize: 15 };
    let filter = { sizes, isInStock, itemType, pageDto };
    let items = await itemFetch.getItemsByFilter(filter)
      .then(res => res.json())
      .then(items => items);
    await itemsForeach(items, true)
  })
}

async function resetFilter() {
  await $('#filterReset').click(async () => {
    $('body input:checkbox:checked').each(function () {
      $(this).prop('checked', false)
    });
    $('#itemTypeFilter').val('Все')
    $('#showElse').prop('hidden', false);
    $('#showElse').val(1)
    $('#searchItems').val('')
    await getItems(true, 0)
  })
}
