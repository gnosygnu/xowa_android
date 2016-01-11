var transformer = require("../transformer");
var utilities = require("../utilities");

function shouldAddImageOverflowXContainer(image) {
    if ((image.width > document.getElementById('content').offsetWidth) && !utilities.isNestedInTable(image)) {
        return true;
    } else {
        return false;
    }
}

function addImageOverflowXContainer(image, ancestor) {
    image.setAttribute('hasOverflowXContainer', 'true'); // So "widenImages" transform knows instantly not to widen this one.
    var div = document.createElement( 'div' );
    div.className = 'image_overflow_x_container';
    ancestor.parentElement.insertBefore( div, ancestor );
    div.appendChild(ancestor);
}

function maybeAddImageOverflowXContainer() {
    var image = this;
    if (shouldAddImageOverflowXContainer(image)) {
        var ancestor = utilities.firstAncestorWithMultipleChildren(image);
        if (ancestor) {
            addImageOverflowXContainer(image, ancestor);
        }
    }
}

transformer.register( "addImageOverflowXContainers", function( content ) {
    // Wrap wide images in a <div style="overflow-x:auto">...</div> so they can scroll
    // side to side if needed without causing the entire section to scroll side to side.
    var images = content.getElementsByTagName('img');
    for (var i = 0; i < images.length; ++i) {
        // Load event used so images w/o style or inline width/height
        // attributes can still have their size determined reliably.
        images[i].addEventListener('load', maybeAddImageOverflowXContainer, false);
    }
} );