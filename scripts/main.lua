require("scripts/functions")
require("scripts/lang")

print("Patching...")
patchAchivements(sugarCubePath, imageeditor, "a", "achivements");
patchAchivements(sugarCubePath, imageeditor, "c", "collections");
fileutil:copy(sugarCubePath .. "/Data/Image/Title/Achievement/", sugarCubePath .. "/Data/Image/Award")
patchSpaceBar(imageeditor, sugarCubePath)
patchNotices(sugarCubePath, imageeditor)
patchKoreanNotice(sugarCubePath, imageeditor)
print("Patching done!")