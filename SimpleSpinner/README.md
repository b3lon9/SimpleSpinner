#### Spinner Structure <br />

```
- 클릭시 Popup이 View아래에 띄어져야함 : (2023.03.21) ▷ Anchor fix 찾을 예정
- 띄어진 Popup에 아이템이 있어야함 : (2023.03.21)
- 선택한 아이템은 View에 표시되어야함 : (2023.03.22)

- Popup크기가 View크기와 동일하도록 수정 : (2023.03.23)
- Popup 표출 위치가 View의 상단, 하단 Boolean상태로 설정창 구성
- Popup 표출 위치 offset X, Y 설정창 구성
- Popup 레이아웃 setter 구성 : (2023.03.27)
- Builder 구성
▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
- attr 구성 : (2023.03.27)
    - background : (2023.03.28)
    - pivot
    - showDropDown Location
    - popup item height : (2023.03.28)
    - ... 
    
- Popup 애니메이션 적용 : (2023.03.23)
- Popup 애니메이션 표출 시간(Duration) 조절 가능? ▶ 불가능
- View 화살표 애니메이션 적용 : (2023.03.28) 
    - 애니메이션 적용 불가 하지만, 따로 커스텀 레이아웃을 넣고 해당 id를 참조하여 animate할 수 있을 듯
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.custom_animation);
        animation.setDuration(1000);
        view.startAnimation(animation);
- View 화살표 위치 설정 적용 : (2023.03.28) 
    - 위치 설정 불가 이미지 대체
- Popup Instance Name변경 popupWindow -> spinner : (2023.03.28)
▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
[Code Order] Programmatically : setAdapter() → Class : onLayout() 

- Adapter Flexible (Overloading)
    - String[] 형태 (2023.03.30) ▶ Charsequence
    - String 단순 ADD형태 
    - ArrayList 형태

- 저작권 생성 : (2023.03.23)
```