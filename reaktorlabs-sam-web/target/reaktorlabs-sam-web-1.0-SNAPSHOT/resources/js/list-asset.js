function checkAll(formname, checktoggle)
{
  var checkboxes = new Array(); 
  checkboxes = document[formname].getElementsByClassName('select-all');
 
  for (var i = 0; i < checkboxes.length; i++)  {
      checkboxes[i].checked = checktoggle;
  }
}

function setCheckAll(formname)
{
  var checkboxes = new Array(); 
  checkboxes = document[formname].getElementsByClassName('select-all');
  var checkAll = new Array(); 
  checkAll = document[formname].getElementsByClassName('check-all');
  var checkState = true;
 
  for (var i = 0; i < checkboxes.length; i++)  {
      if(checkboxes[i].checked == false) {
          checkState = false;
      }
  }
  checkAll[0].checked = checkState;
}
