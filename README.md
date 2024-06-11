# SimpleStarterKits
This simple yet powerful plugin gives new players starter kits.
<br/><br/>

**How to install:** (spoilers)
1. Download the jar file (image arrow at download)
2. Drop it in your plugins folder (arrow at folder)
3. Reload or restart your server (image with two arrows)
<br/><br/>

**How to use:**
1. Fill your inventory with the items you want to set as the starter kit. This works for written books, armor, offhand 
items, enchanted items, custom nbt data, etc.
2. Type "/simplestarterkits set" in the chat and press enter.
3. There is no three. It's as simple as that.


Want to check what your new players are getting? Enter "/simplestarterkits give" in the chat. You'll receive a starter 
kit so you can verify what your players are getting.
<br/><br/>

**Why you should use this instead of other plugins:**
1. It's up-to-date. Unlike (starter kits [starterkits](https://www.spigotmc.org/resources/starterkit-1-8-1-13-2.49445/), https://dev.bukkit.org/projects/starter-kit, or https://dev.bukkit.org/projects/starterkit), it doesn't 
generate errors, and it works on the latest version of minecraft.
2. It's powerful. Unlike [firstjoinplus](https://dev.bukkit.org/projects/firstjoinplus), it saves item location, nbt data, and custom data.
3. It's simple. Unlike [simple kits](https://www.spigotmc.org/resources/playerkits-fully-configurable-kits-1-8-1-19.75185/), this is a starter kit plugin, not a kits plugin. That means we're dedicated to making starter kits as easy to use as possible. You don't need to go through complicated configs or documentation
<br/><br/>

**Features:**
* Save kits with one command!
* Preserves item enchantments, lore, location in inventory and more!
* Data is saved in .yml files for easy editing!
<br/><br/>

**Example kits:**<br/>
(banners arranged in hi)<br/>
(written book with colors)<br/>
(potions, spawn eggs, fireworks, and command blocks)<br/>
(dyed leather armor with unbreakable lore & name sword with banner shield with skulls and potions)<br/>
(custom enchants and some other plugin)<br/>
If you can get it, SimpleStarterKits can set it!
<br/><br/>

**Configs:**<br/>
(spoiler: Config.yml (Main config))
#Some minimal settings (You don't have to edit them)


#Give kits to new players? (true,false)
give: true

#What the starter kit is saved under in kits.yml
starter-kit-name: "starter-kit"

#Message if the player already has items in their inventory
conflict-message: "You received a starter kit, but you already had items in your inventory! The conflicting items were dropped."

#Are new players ones not seen before by the server or the plugin? (server,plugin)
#For example, a player who played on the server before the plugin was installed would not have been seen before by the plugin
#Players who the plugin has seen before are stored in played_before.yml
new: "plugin"

(spoiler: kits.yml)
(kits.yml)

(spoiler: played_before.yml)

<br/><br/>

**Full list of savable attributes (copied from bob):**<br/>
Location in inventory.<br/>
Name, Lore.<br/>
Enchantments.<br/>
Potion Effects.<br/>
Leather Armor Color.<br/>
Book Enchantments.<br/>
Written Books.<br/>
Fireworks Attributes.<br/>
Banner, Shields Attributes.<br/>
Skull Textures.<br/>
Item Flags.<br/>
Unbreakable Tag.<br/>
NBT Tags.<br/>
Attributes Modifiers.<br/>
%player% variable in name and lore.<br/>
PlaceholderAPI static variables in name and lore.<br/>