var contextPath = "${pageContext.request.contextPath}";

//아이디 중복검사
function idcheck(){
	
	var id = $("#memId").val();
	var IDpattern = new RegExp(/^[A-Za-z0-9_]+[@]+[a-z.]+$/);
	
	// 아이디
	if(id == ""){
		alert("Email를 입력 하세요.");
		$("#memId").focus();
		return false;
	}
	else if(!IDpattern.test(id)){
		alert("이메일 형식으로 입력하세요.");
		return false;
	}
	
	// alert(id);
	$.ajax({
		type: "POST",
		url: "/idcheckB",
		data: {"id": id},
		success: function(cnt){
			if(cnt==1){
			 	$("#vaildEmail").text("이미 존재하는 이메일 입니다.").css("color", "red");	
			 	$("#memId").focus();	
			 	return false;
			}else{
				console.log(cnt);
				$("#vaildEmail").text("사용가능한 아이디 입니다.").css("color","green");
			}
    	}
	});//ajax
	
}

function check(){
	
			var id = $("#memId").val();
			var passwd = $("#memPw").val();
			var passwd2 = $("#memPw2").val();
			var nick = $("#memNick").val();
			var name = $("#memName").val();
			var birth = $("#memBirth").val();
			var tel = $("#memTel").val();
			
			//자격증
			var certName = $("#certName").val();
			var certReg = $("#certReg").val();
			var certDepart = $("#certDepart").val();
			
			//정규식
			var IDpattern = new RegExp(/^[A-Za-z0-9_]+[@]+[a-z.]+$/);	//RegExp객체 형식
			var pattern = new RegExp(/^[A-Za-z0-9_]+$/);
			var num = /^[0-9]+$/;              							//리터럴 형식
			
			// 아이디
			if(id == ""){
				alert("Email를 입력하세요.");
				$("#memId").focus();
				return false;
			}
			else if(!IDpattern.test(id)){
				alert("이메일 형식으로 입력하세요.");
				return false;
			}
			
			// 비밀번호
			if(passwd == ""){
				alert("비밀번호를 입력 하세요");
				$("#memPw").focus();
				return false;
			}
			else if(!pattern.test(passwd)){
				alert("비밀번호는 영문 대소문자, 숫자, 언더바(_)만 사용할 수 있습니다.");
				return false;
			}
			if(passwd2 == ""){
				alert("비밀번호 확인 입력 하세요");
				$("#memPw").focus();
				return false;
			}
			else if(!pattern.test(passwd2)){
				alert("비밀번호는 영문 대소문자, 숫자, 언더바(_)만 사용할 수 있습니다.");
				return false;
			}
			if(passwd != passwd2){
				alert("비밀번호가 다릅니다");
				$("#memPw").val("");
				$("#memPw2").val("");
				$("#memPw").focust();
				return false;
			}
			
			// 닉네임
			if(nick == ""){
				alert("닉네임을 입력 하세요.");
				$("#memNick").focus();
				return false;
			}
			
			// 이름
			if(name == ""){
				alert("이름을 입력 하세요");
				$("#memName").focus();
				return false;
			}
			
			// 생년월일
			if(birth == ""){
				alert("생년원일을 입력 하세요");
				$("#memBirth").focus();
				return false;
			}
			else if (isNaN(birth)) {
				alert("생년월일은 숫자만 입력 하세요.");
				$("#memBirth").focus();
				return false;
			}
			
			// 전화번호
			if(tel == ""){
				alert("전화번호를 입력 하세요");
				$("#memTel").focus();	
				return false;
			}
			else if(isNaN(tel)){
				alert("전화번호는 숫자만 입력 하세요.");
				$("#memTel").focus();
				return false;
			}
			
			//자격증 table > 비즈니스 회원 회원가입시에만 적용
			if(certName != null || certReg !=null || certDepart !=null){
				
				// 자격증 이름
				if(certName == ""){
					alert("자격증 이름을 입력 하세요.");
					$("#certName").focus();
					return false;
				}
				
				//자격증 등록날짜
				if(certReg == ""){
					alert("자격증 등록날짜를 입력 하세요.");
					$("#certReg").focus();
					return false;	
				}
				else if(isNaN(certReg)){
					alert("자격증 등록날짜는 숫자만 입력하세요.");
					return false;
				}
				// 자격증 발급기관
				if(certDepart == ""){
					alert("자격증 발급기관을 입력 하세요.");
					$("#certDepart").focus();
					return false;
				}
			}
			
		}
 