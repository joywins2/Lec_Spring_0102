function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
	
	console.log("getFileInfo called...");
		
	var fileName,imgsrc, getLink, fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "/displayFile?fileName="+front + end;
		
	}else{
		//...현재 위치인 zjs에서 상대경로로 dist/img폴더로 이동함.
		imgsrc ="../dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}


