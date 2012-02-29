require("scripts/functions")
require("scripts/lang")

sugarCubePath = "C:/Program Files (x86)/Desura/Common/Sugar Cube Bittersweet Factory"

function getImageEditor()
	return imageeditor
end
print("Patching...")
patchAchivements(sugarCubePath, imageeditor, "a", "achivements");
patchAchivements(sugarCubePath, imageeditor, "c", "collections");
fileutil:copy(sugarCubePath .. "/Data/Image/Title/Achievement/", sugarCubePath .. "/Data/Image/Award")
patchSpaceBar(imageeditor, sugarCubePath)
patchNotices(sugarCubePath, imageeditor)
patchKoreanNotice(sugarCubePath, imageeditor)
print("Patching done!")