var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "smartEditor",
		sSkinURI : "/resources/smartEditer2/SmartEditor2Skin.html",
		fCreator : "createSEditor2",
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : false,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger : false
		}
	})
	
		

