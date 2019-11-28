# CS349 A4
Student: nhung
Marker: Alexandra Vtyurina


Total: 38 / 100 (38.00%)

Code: 
(CO: won’t compile, CR: crashes, FR: UI freezes/unresponsive, NS: not submitted)


Notes:   


## I. Deliverables (10%)

1. [5/5] Code compiles and runs, and includes an APK for grading.


2. [5/5] Includes a README.txt describing the development environment, and enhancements that you implemented.


## II. Basic Functionality (40%)

3. [0/5] Selection tool. The user can select this tool in the toolbar then click / touch on a shape to select it. There should be some visual indication which shape is selected. Pressing ESC on the keyboard will clear shape selection.

--5 Selection tool not implemented or doesn't work. 

4. [0/5] Erase tool. The user can select an an existing shape on the canvas, and then touch this tool in the toolbar to delete it from the canvas. The Erase tool should be disabled if there is no tool selected on the canvas.

--5 Erase tool not implemented or doesn't work.

5. [0/5] Line drawing tool. The user can select this tool in the toolbar and then draw lines in the canvas by holding and dragging a finger across the canvas, using the selected colour.

--5 Line drawing tool not implemented or doesn't work.

6. [3/5] Rectangle drawing tool. The user can select this tool in the toolbar and then draw rectangles in the canvas by holding and dragging a finger, using the selected colour.

--2 Rectangle drawing doesn't work as expected (the first corner of the rectangle should correspond to the position of touch-down event, and the second corner should correspond to the position of the touch-up event)

7. [3/5] Circle drawing tool. The user can select this tool in the toolbar and then draw circles or ellipses in the canvas by holding and dragging a finger, using the selected colour.

--2 Circle drawing doesn't work as expected (the first corner [or centre] of the circle/ellipse should correspond to the position of touch-down event, and the second corner [or radius] should correspond to the position of the touch-up event)

8.  [2/5] Colour palette. Displays at least 3 colours in a graphical view, which the user can select to set the property for drawing a new shape.

--3 Colours are not displayed graphically.

9. [10/10] Preview. Each of the drawing operations should show a preview (e.g. wireframe) as the shape is being drawn.


## IV. Shape Selection, Movement, and Preview (15%)

10.  [0/5] Selecting a shape will update the color palette with the shape's color value.

--5 The colour palette is not updated to relect the selected shape.

12.  [0/10] Moving shapes. Users can move shapes around the screen by selecting, then dragging them. There should be an indication which shape is selected. [5 marks for moving, 5 marks for selection indicator]

--10 It's not possible to move shapes

## V. Responsiveness and Orientation (25%)

13. [5/5] The application's horizontal layout shows the canvas, tool bar, and colour palette correctly.


14. [5/5] The application's vertical layout shows the canvas, tool bar, and colour palette correctly.


15. [0/10] The application correctly displays the canvas and its contents when transitioning between the horizontal and vertical layouts.

--10 content disappears on rotation

15. [0/5] Data is not lost when moving between device orientations. (i.e. data should not be cleared).

--5 data is lost or the canvas clears during orientation change.

## VI. Enhancements (10%)

16. [0/10] Application incorporates one or more enhancements up to 10%, as described in the requirements.

+10     Save / Export as PDF, JPEG, PDF: The application supports saving or exporting as JPEG/PNG/PDF with a share action showing the native share screen.
+10     Image Importing: The application supports import images from a photo gallery (or from a URL) as a static object onto the canvas. (Note: The image should be moveable on the canvas. If it is not moveable, only +5 marks.)
+10     Multitouch Support (Canvas): The application supports scaling the canvas by pinch-to-zoom and pan (i.e. moving the canvas when zoomed) using two-finger drag. 
+10     Multitouch Support (Shapes): The application supports scaling shapes by pinch-to-zoom (i.e. select the shape, then pinch-in or pinch-out to change the size of that shape).
+10     Un-Do/Re-Do Support: The application allows users to undo/redo the last draw or action on canvas objects. Support undoing at least 5 actions. (If fewer than 5 actions, only +5 marks.)
