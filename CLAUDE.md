@~/Documents/Projects/Minecraft/MinecraftDeveloperPortal/.claude/profiles/legacy-forge.md

# Rustic Delight - `version/1.20`

**This file describes the `version/1.20` line**: Minecraft 1.20.1, Forge.
Built with ForgeGradle (legacy, maintenance only).

It belongs to whichever folder has this branch checked out - the main `RusticDelight` folder or a worktree
under `RusticDelight/.worktrees/`. A session started in a worktree also loads the main folder's CLAUDE.md,
which describes another line; for this folder, this file is the one that applies. Confirm with
`git branch --show-current`. `MinecraftDeveloperPortal/data/mods.json` lists every line of the mod.

A rustic add-on for Farmer's Delight: coffee, cotton, bell peppers, pancakes, calamari and more.
Mod ID `rusticdelight`.

Split build: the Fabric lines are `fabric/*` (Loom); the NeoForge and Forge lines are `version/*`
(ModDevGradle, ForgeGradle), single-loader despite the prefix.

On the Fabric lines, Farmer's Delight Refabricated comes from the Modrinth maven as `<mc>-<fdr>`,
published against the base MC version (`dependency-sources.md`). REI and JEI availability lags the ladder; run
`/mc-check-deps` before assuming one exists for a rung.

## This line

- **Forge-only line (ForgeGradle) despite the `version/` prefix.**
- Java 17, Mojang mappings with Parchment.
- Datagen: `runData`. Output: `src/generated/resources`, never hand-edited.
- No game tests yet. Writing the first one for whatever is ported next is the highest-value test available (`verification.md`).
- Published with `publishMods` from `build.gradle`, with the `-PpublishDryRun` flag. Uploads are tagged from `supported_minecraft_versions`.
- Hand-authored access transformer: `src/main/resources/META-INF/accesstransformer.cfg`. A first suspect when a port fails to load.
