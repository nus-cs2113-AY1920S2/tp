[comment]: # (@@author NizarMohd)
<!-- top button was extracted from https://www.w3schools.com/howto/howto_js_scroll_to_top.asp -->
<style>
img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: auto;
}
    
#topButton {
  display: none; 
  position: fixed; 
  bottom: 20px;
  right: 30px; 
  z-index: 99; 
  border: none; 
  outline: none; 
  background-color: #0ED7D3; 
  color: white; 
  cursor: pointer; 
  padding: 15px; 
  border-radius: 10px; 
  font-size: 18px; 
  text-alignment: center;
}

#topButton:hover {
  background-color: #15E3DF;
}
</style>

<button onclick="topFunction()" id="topButton" title="Go to top">Top</button>

<script src="jsCodes/topButton.js"></script>


# OrgaNice!

  OrgaNice! is a task manager integrated with a Study Area search function and a Notes Taking feature. 
  You can use it to manage your tasks, be it events or deadlines. On top of that, 
  it is capable of assisting students in finding Study Areas that meet their desired criteria.
  Also, our Notes Taking feature provides you with a handy way to record notes based on school modules fast.<br>

![UI](images/ui.png)

Useful links:
* [User Guide](UserGuide.md)
* [Developer Guide](DeveloperGuide.md)
* [About Us](AboutUs.md)
