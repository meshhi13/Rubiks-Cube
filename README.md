# Rubik's Cube JavaFX App

A fully interactive 3D Rubik's Cube simulator built with JavaFX.  
Rotate, scramble, and reset the cube with smooth animations and a modern UI.

![Screen Recording 2025-07-14 at 4 11 37 PM (2)](https://github.com/user-attachments/assets/e6224323-116e-4e9f-8594-a129bc64d4d7)

---

## Features

- **3D Rubik's Cube**: Rotate any face in both directions.
- **Scramble**: Randomizes the cube with visible, animated moves.
- **Reset**: Instantly returns the cube to the solved state.
- **Camera Controls**: Zoom in/out and reset the view.
- **Modern UI**: Clean, responsive buttons with icons and hover effects.

---

## Controls

| Button/Icon | Action                |
|-------------|-----------------------|
| 🎲          | Scramble the cube     |
| 🔄          | Reset the cube        |
| ⤾           | Reset camera/view     |
| + / -       | Zoom in/out           |
| F, R, U...  | Rotate faces          |
| F', R', U'  | Rotate faces (reverse)|

---

## How to Run

1. **Clone the repository:**
    ```sh
    git clone https://github.com/meshhi13/Rubiks.git
    cd Rubiks
    ```

2. **Open in your IDE** (e.g., VS Code or IntelliJ).

3. **Run the main class** (usually `RubiksCubeMain.java` or similar).

4. **Enjoy!**

---

## Project Structure

```
Rubix/
├── RubiksCubeMain.java
├── RubiksCube3DApp.java
├── RubiksCubeMain.java
├── RubiksSide.java
├── Util/
│   ├── UIUtil.java
│   └── InteractionUtil.java
│   └── ColorUtil.java
│   └── LightingUtil.java
```

---

## Customization

- **Colors:** Change the `colorPalette` in `RubiksCube.java`.
- **Scramble Moves:** Adjust the number or speed in `scramble()`.

---

## Credits

- Built with [JavaFX](https://openjfx.io/).

---

## License

MIT License

---

*Happy cubing!*
