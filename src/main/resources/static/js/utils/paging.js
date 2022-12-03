async function pagingNext(isSearch) {
  let showElse = $('#showElse');
  let val = showElse.val();
  console.log(isSearch)
  if (isSearch) {
    await getItems(true, val);
  } else {
    await getItems(false, val);
  }
  let page = parseInt(val) + parseInt(val)
  showElse.val(page)
}
