function setFund(){
	var img = document.getElementsById("image");
	img.src = this.value;
	return false;
}
document.getElementsById("FundList").onchange = setFund;