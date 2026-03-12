# Applicant Notes

## How to run
- Open project in Android Studio.
- Build and run on emulator or device.
- On the main screen, click the **"View Farms"** button to open the Farms list.

## New screen code location
- All new Kotlin files are in:  
  `app/src/main/java/com/hellotractor/app/features/farms/`
    - FarmsFragment.kt
    - FarmsViewModel.kt
    - FarmsContract.kt
    - FarmsAdapter.kt
- Layout files are in:  
  `app/src/main/res/layout/`
    - fragment_farms.xml
    - item_farm.xml

## Known limitations / TODOs
- Date formatting is raw; could be improved to a user-friendly format.
- Pull-to-refresh is not implemented (optional).
- Unit tests not included (but sorting logic is handled in ViewModel).