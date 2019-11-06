# ChoLog
MakeUs_Project_Team_ChoLog_Android

# 10.17 
 Splash Activity : Logo 적용 필요, 로그인 생길 시 자동 로그인 서비스 적용 필요
 
 Main Activity 
   - My Info : 아이템 (o),
   - Home : 아이템(미완성), RecyclerView Item 간격 조정 클래스 정의함

# 10.18
 Main Activity
   - CollapsingToolBarLayout 적용 (o), ScrollBar UI 적용
   - Fragment 다 적용(둘러보기 클릭 시 ToolBar의 금액부분 안보이게 조정 필요)
   - My Info : ToolBar 속 다른 TextView 적용 필요
   - Logo 클릭 이벤트 받는거 확인, 클릭시 fragment속 scroll 상단으로 이동 필요

 ServiceAddActivity
   - layout 완료
   
# 10.19
 ServiceAddActivity
   - Product Activity 연결(찾기 버튼)
   - 마지막 결제일 부분 Spinner 적용 필요
   - 삭제 버튼 시 하단 바 변경 ok

 MainActivity
   - 상단 월,월이용료 부분 '내정보', '홈', '둘러보기'에 따라 다르게 구분 완료
   - 로그인 Dialog 비율 수정 필요
   - fragment 바뀔시 딜레이 부분에 대해 찾아보기

# 10.20
 ServiceAddActivity
  - DatePicker Spinner Dialog로 만들어서 적용하기
  - 상품명 찾기 Activity로 넘어가서 자동 완성 다이얼로그 추후에 적용하기
  
 MainActivity
   - Dialog height는 적용, weight match_parent상태인데 비율 95정도로 수정 필요
   - 둘러보기 fragment ( 카테고리를 제외한 layout 적용 완료)
   - Fragment 전환 시 AppBar부분 보다 Fragment 교체를 먼저 적용 시키기

# 10.22
 - 전체적인 UI Color 변경
 - ServiceAddActivity 내용 수정 및 UI 변경
 - HomeFragment Item 구성 변경

 # 10.25
 - 삭제 Dialog, Login Dialog 비율 수정
 - DetailActivity 삭제
 - CurrencyActivity 추가 (화폐 단위 결정 화면)

# 10.27
 - RemoveDialog 비율 다시 수정해야함
 - ServiceAddActivity 
	- ProductActivity 생성(o)
	- 마지막 결제일, 알람, 주기 Dialog 생성해야함 
	- ProductActivity에서 Brand 부분 AutocompleteTextView 적용(o), Shadow 없애야함
 - HomeItem 내용 수정, 실제 적용 해야함 (날짜 계산 필요)
 - 둘러보기 부분 모두보기는 모두 Fragment로 생성해야함...

# 10.28
 - RemoveDialog 비율 수정 완료, 처음에 넘겨받은 index를 이용해서 onResult 에서 삭제하기
 - ServiceAddActivity
	- 마지막 결제일, 주기, 알람 Dialog layout 작성중 (DatePicker Spinner Mode 사용)

# 10.31
 - ServiceAddActivity
	- 마지막 결제일, 주기, 알람 Dialog Layout 작성 완료 (비율 맞추기)

# 11.03
 - ServiceAddActivity
	- 다이얼로그들 애니메이션 추가
 - HomeFragment
	- Home Recyclerview의 item의 멤버변수들 조율
	- HomeAdapter 조율, 받은 날짜와 주기로 다음 결제일 계산 해야함

# 11.06
 - ServiceAddActivity
	- 자동 환율 적용
 	- 서비스 관리 적용시 아이템 값 넘겨 받아서 field 채우기 적용
	- 홈 아이템 SharedPreference에 저장

